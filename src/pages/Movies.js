import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function Movies(){
  const [events,setEvents] = useState([]);
  useEffect(()=>{
    axios.get('/api/events').then(res=>setEvents(res.data)).catch(()=>{});
  },[]);
  return (<div>
    <h1>Events</h1>
    <Link to="/login">Login</Link> | <Link to="/register">Register</Link>
    <ul>
      {events.map(e=> (<li key={e.id}>
        <b>{e.title}</b> - {e.venue} - Seats: {e.seatsAvailable}/{e.totalSeats} - <Link to={`/events/${e.id}/seats`}>Book</Link>
      </li>))}
    </ul>
  </div>);
}
