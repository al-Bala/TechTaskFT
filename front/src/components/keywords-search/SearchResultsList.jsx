import React from "react";
import { SearchResult } from "./SearchResult";
import "/src/css/SearchResultsList.css"

export const SearchResultsList = ({ results, clickedResults, setClickedResults }) => {

  const handleResultClick = (result) => {
    setClickedResults(prevClickedResults => [...prevClickedResults, result]);
  };

  const handleRemoveClickedResult = (clickedResult) => {
    const updatedClickedResults = clickedResults.filter(item => item !== clickedResult);
    setClickedResults(updatedClickedResults);
  };

  return (
    <div>
      <div className="results-list">
        {results.map((result, id) => (
          <SearchResult
            result={result}
            key={id}
            onClick={() => handleResultClick(result)}
          />
        ))}
      </div>
      <div className="clicked-result-gap">
        {clickedResults.map((clickedResult, id) => (
          <div className="clicked-result" key={id} onClick={() => handleRemoveClickedResult(clickedResult)}>
            {clickedResult}
          </div>
        ))}
      </div>
    </div>
  );
}