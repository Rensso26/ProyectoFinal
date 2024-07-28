import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { over } from '@stomp/stompjs';
import { createPeticion } from './services/PeticionesServices';

const WebSocketComponent = () => {
    const [messages, setMessages] = useState([]);
    const [toyId, setToyId] = useState('');
    const [quantity, setQuantity] = useState(1);
    let stompClient = null;

    useEffect(() => {
        connect();

        return () => {
            disconnect();
        };
    }, []);

    const connect = () => {
        const socket = new SockJS('http://localhost:8080/fabrication-websocket');
        stompClient = over(socket);
        stompClient.connect({}, onConnected, onError);
    };

    const onConnected = () => {
        stompClient.subscribe('/topic/fabrication', onMessageReceived);
    };

    const onMessageReceived = (msg) => {
        const message = msg.body;
        setMessages(prevMessages => [...prevMessages, message]);
    };

    const onError = (err) => {
        console.error('Error connecting to WebSocket', err);
    };

    const disconnect = () => {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const peticion = { toyId, quantity };
        createPeticion(peticion)
            .then(() => {
                setToyId('');
                setQuantity(1);
            })
            .catch((err) => {
                console.error('Error creating peticion', err);
            });
    };

    return (
        <div>
            <h2>Fabrication Messages</h2>
            <ul>
                {messages.map((msg, index) => (
                    <li key={index}>{msg}</li>
                ))}
            </ul>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={toyId}
                    onChange={(e) => setToyId(e.target.value)}
                    placeholder="Toy ID"
                    required
                />
                <input
                    type="number"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                    min="1"
                    required
                />
                <button type="submit">Fabricate Toy</button>
            </form>
        </div>
    );
};

export default WebSocketComponent;
