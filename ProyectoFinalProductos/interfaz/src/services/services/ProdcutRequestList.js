import React, { useState, useEffect } from 'react';
import ProductRequestService from './ProductRequestService';

const ProductRequestList = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    fetchPendingRequests();
  }, []);

  const fetchPendingRequests = async () => {
    try {
      const response = await ProductRequestService.getPendingRequests();
      setRequests(response.data);
    } catch (error) {
      console.error('Error fetching pending requests', error);
    }
  };

  const approveRequest = async (id) => {
    try {
      await ProductRequestService.approveRequest(id);
      fetchPendingRequests();
    } catch (error) {
      console.error('Error approving request', error);
    }
  };

  const rejectRequest = async (id) => {
    try {
      await ProductRequestService.rejectRequest(id);
      fetchPendingRequests();
    } catch (error) {
      console.error('Error rejecting request', error);
    }
  };

  return (
    <div>
      <h2>Pending Product Requests</h2>
      <ul>
        {requests.map(request => (
          <li key={request.id}>
            <p>Product: {request.toy.name}</p>
            <p>User: {request.user.name}</p>
            <button onClick={() => approveRequest(request.id)}>Approve</button>
            <button onClick={() => rejectRequest(request.id)}>Reject</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ProductRequestList;
