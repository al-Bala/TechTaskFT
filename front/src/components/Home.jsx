import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '/src/css/Home.css';

export const Home = () => {
  const [campaigns, setCampaigns] = useState([]);
  const [account, setAccount] = useState(null);
  const [isEditing, setIsEditing] = useState(null);
  const [expandedKeywords, setExpandedKeywords] = useState([]);

  useEffect(() => {
    fetchCampaigns();
    fetchAccountAmount();
  }, []);

  const fetchCampaigns = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/campaigns');
      setCampaigns(response.data);
    } catch (error) {
      console.error('Error fetching campaigns', error);
    }
  };

  const fetchAccountAmount = async () => {
    try {
      const response = await axios.get('http://localhost:8080/account');
      setAccount(response.data);
    } catch (error) {
      console.error('Error fetching setAccount', error);
    }
  };

  const deleteCampaign = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/campaigns/${id}`);
      setCampaigns(campaigns.filter(campaign => campaign.id !== id));
    } catch (error) {
      console.error('Error deleting campaign', error);
    }
  };

  const startEditing = (campaign) => {
    setIsEditing(campaign.id);
  };

  const stopEditing = () => {
    setIsEditing(null);
  };

  const handleInputChange = (id, field, value) => {
    setCampaigns(campaigns.map(campaign => campaign.id === id ? { ...campaign, [field]: value } : campaign
    ));
  };

  const updateCampaign = async (id) => {
    const campaignToUpdate = campaigns.find(campaign => campaign.id === id);
    try {
      const response = await axios.patch(`http://localhost:8080/api/campaigns/${id}`, campaignToUpdate);
      setCampaigns(campaigns.map(campaign => campaign.id === id ? response.data : campaign
      ));
      setIsEditing(null);
      fetchAccountAmount();
    } catch (error) {
      console.error('Error updating campaign', error);
    }
  };

  const toggleKeywords = (id) => {
    setExpandedKeywords((prev) => prev.includes(id) ? prev.filter((item) => item !== id) : [...prev, id]
    );
  };

  return (
    <div className="home-container">
      <div className="account-info">Your account: {account}</div>
  
      <h1>Your Campaigns</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Keywords</th>
            <th>Bid Amount</th>
            <th>Fund</th>
            <th>Status</th>
            <th>Town</th>
            <th>Radius (km)</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {campaigns.map(campaign => (
            <tr key={campaign.id}>
              <td>{campaign.id}</td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="text"
                    value={campaign.name}
                    onChange={(e) => handleInputChange(campaign.id, 'name', e.target.value)}
                  />
                ) : (
                  campaign.name
                )}
              </td>
              <td>
                <button onClick={() => toggleKeywords(campaign.id)}>
                  {expandedKeywords.includes(campaign.id) ? 'Hide' : 'Show'}
                </button>
                {expandedKeywords.includes(campaign.id) && (
                  <ul className="keywords-list">
                    {Array.isArray(campaign.keywords) ? (
                      campaign.keywords.map((keyword, index) => (
                        <li key={index}>{keyword}</li>
                      ))
                    ) : (
                      <li>{campaign.keywords}</li>
                    )}
                  </ul>
                )}
              </td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="number"
                    value={campaign.bidAmount}
                    onChange={(e) => handleInputChange(campaign.id, 'bidAmount', e.target.value)}
                  />
                ) : (
                  campaign.bidAmount
                )}
              </td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="number"
                    value={campaign.fund}
                    onChange={(e) => handleInputChange(campaign.id, 'fund', e.target.value)}
                  />
                ) : (
                  campaign.fund
                )}
              </td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="checkbox"
                    checked={campaign.isStatusOn}
                    onChange={(e) => handleInputChange(campaign.id, 'isStatusOn', e.target.checked)}
                  />
                ) : (
                  campaign.isStatusOn ? 'On' : 'Off'
                )}
              </td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="text"
                    value={campaign.town}
                    onChange={(e) => handleInputChange(campaign.id, 'town', e.target.value)}
                  />
                ) : (
                  campaign.town
                )}
              </td>
              <td>
                {isEditing === campaign.id ? (
                  <input
                    type="number"
                    value={campaign.radiusKm}
                    onChange={(e) => handleInputChange(campaign.id, 'radiusKm', e.target.value)}
                  />
                ) : (
                  campaign.radiusKm
                )}
              </td>
              <td className="actions-buttons">
                {isEditing === campaign.id ? (
                  <>
                    <button onClick={() => updateCampaign(campaign.id)}>Save</button>
                    <button onClick={stopEditing}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button className="button-edit" onClick={() => startEditing(campaign)}>Edit</button>
                    <button className="button-delete"onClick={() => deleteCampaign(campaign.id)}>Delete</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div>
        <Link to="/add-product">
          <button className="next-button">Add Campaign</button>
        </Link>
      </div>
    </div>
  );
};
