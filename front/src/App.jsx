import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddForm from './components/AddForm';
import { Home } from './components/Home';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-product" element={<AddForm />} />
      </Routes>
    </Router>
  );
};

export default App;