import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Movies from './pages/Movies';
import Seats from './pages/Seats';

function App(){ 
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Movies/>} />
        <Route path="/login" element={<Login/>} />
        <Route path="/register" element={<Register/>} />
        <Route path="/events/:id/seats" element={<Seats/>} />
      </Routes>
    </BrowserRouter>
  );
}

const root = createRoot(document.getElementById('root'));
root.render(<App/>);
