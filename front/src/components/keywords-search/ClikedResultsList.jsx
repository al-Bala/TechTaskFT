import React from "react";

export const SearchResult = ({ result, onClick }) => {
  const handleClick = async () => {
    onClick(result);
  };

  return (
    <div className="search-result" onClick={handleClick}>
      <div>{result}</div>
    </div>
  );
};