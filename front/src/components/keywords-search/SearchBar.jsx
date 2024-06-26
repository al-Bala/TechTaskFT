import React, {useState} from "react";

export const SearchBar = ({setResults}) => {
    const [input, setInput] = useState("");

    const fetchData = (value) => {
        fetch("http://localhost:8080/keywords?query=" + value)
            .then((response) => response.json())
            .then((json) => {
                console.log(json)
                setResults(json)
            });
    };

    const handleChange = (value) => {
        setInput(value)
        fetchData(value)
    };

    return (
    <div>
        <input
            type="text"
            name="keywords"
            value={input}
            onChange={(e) => handleChange(e.target.value)}
            placeholder="Enter keywords"
            required
          />
    </div>
    );
}