import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { SearchBar } from './keywords-search/SearchBar'
import { SearchResultsList } from './keywords-search/SearchResultsList';
import { Link } from 'react-router-dom';
import { Fund } from './Fund';
import '/src/css/AddForm.css';

const BidForm = () => {
  const [results, setResults] = useState([]);
  const [clickedResults, setClickedResults] = useState([]);
  const [fund, setFund] = useState("");
  const [formData, setFormData] = useState({
    name: '',
    keywords: [],
    bidAmount: '',
    fund: '',
    town: '',
    radiusKm: '',
    isStatusOn: false
  });

  useEffect(() => {
    fetch('http://localhost:8080/account')
      .then(response => response.json())
      .then(data => {
        setFund(data);
      })
      .catch(error => {
        console.error('Error fetching fund data:', error);
      });
  }, []);


  const handleInputChange = (event, customName, customValue) => {
    const { name, value, type, checked } = event.target;
    const newValue = type === 'checkbox' ? checked : value;
    setFormData(prevFormData => ({
      ...prevFormData,
      [customName || name]: customValue || newValue
    }));
  };

  const submitFormData = async (formData) => {
    try {
      const response = await axios.post('http://localhost:8080/api/campaigns', formData);
      console.log('Form data sent successfully:', response.data);
    } catch (error) {
      console.error('Error sending form data:', error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const updatedFormData = { ...formData, keywords: clickedResults };
    console.log(updatedFormData);
    submitFormData(updatedFormData);
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleInputChange}
            placeholder="Enter your name"
          required
          />
        </div>

        <div className="form-group">
          <label>Keywords:</label>
          <SearchBar setResults={setResults} />
          <SearchResultsList results={results} clickedResults={clickedResults} setClickedResults={setClickedResults} />
        </div>

        <div className="form-group">
          <label>Bid Amount:</label>
          <input
            type="number"
            name="bidAmount"
            value={formData.bidAmount}
            onChange={handleInputChange}
            placeholder="Enter bid amount"
          required
          />
        </div>

        <div className="form-group">
          <label>Fund:</label>
          <Fund setFund={setFund} handleInputChange={handleInputChange} />
          <div>Your account balance:
            <div>{fund}</div>
          </div>
        </div>

        <div className="form-group">
          <label>Town:</label>
          <input
            type="text"
            name="town"
            value={formData.town}
            onChange={handleInputChange}
            placeholder="Enter town name"
          />
        </div>

        <div className="form-group">
          <label>Radius:</label>
          <input
            type="number"
            name="radiusKm"
            value={formData.radiusKm}
            onChange={handleInputChange}
            placeholder="Enter radius"
          required
          />
        </div>

        <div className="form-group">
          <label>
            Status:
            <input
              type="checkbox"
              name="isStatusOn"
              checked={formData.isStatusOn}
              onChange={handleInputChange}

            />
          </label>
        </div>

        <div className="button-container">
          <Link to="/">
            <button className="next-button">Back</button>
          </Link>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
};

export default BidForm;
