// src/App.jsx
import { useEffect, useState } from "react";

export default function App(){
  const [vagas, setVagas] = useState([]);
  useEffect(()=>{
    fetch(import.meta.env.VITE_API_URL + "/api/vacancies")
      .then(r => r.json())
      .then(setVagas)
      .catch(console.error);
  },[]);
  return (
    <div>
      <h1>Movu — Vagas</h1>
      <ul>
        {vagas.map(v => <li key={v.id}>{v.title} — {v.city} — R$ {v.salary}</li>)}
      </ul>
    </div>
  );
}
