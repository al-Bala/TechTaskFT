import React, {useState} from "react";

export const Fund = ({setFund, handleInputChange}) => {
    const [input, setInput] = useState("");

    const fetchData = (value) => {
        fetch("http://localhost:8080/fund?value=" + value)
            .then((response) => response.json())
            .then((json) => {
                setFund(json)
            });
    };

    const handleChange = (value) => {
        setInput(value)
        handleInputChange({ target: { name: 'fund', value: value } }, 'fund', value);
        fetchData(value)
    };

    return (
    <div>
        <input
            type="number"
            step="100"
            name="fund"
            value={input}
            onChange={(e) => handleChange(e.target.value)}
            placeholder="Enter fund amount"
            required
          />
    </div>
    );
}