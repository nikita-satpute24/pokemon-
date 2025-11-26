import { useState } from "react";

export default function SearchBar({onSearch})
{
    const [name, setName ] = useState("");

    const handleSubmit = (e) => 
    {
        e.preventDefault();

        if(name.trim() !== "")
        {
            onSearch(name);
        }
    };

    return(

        <form onSubmit={handleSubmit} className="search-container">

            <input
                type="text"
                placeholder="Enter Pokemon name"
                onChange={(e) => setName(e.target.value)} />

            <button type="submit">Search</button>
        </form>

    );
}