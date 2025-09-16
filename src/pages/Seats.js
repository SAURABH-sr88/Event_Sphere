import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

export default function Seats(){
  const { id } = useParams();
  const [event, setEvent] = useState(null);
  const [selected, setSelected] = useState([]);
  const nav = useNavigate();

  useEffect(()=>{
    axios.get('/api/events/'+id).then(res=>setEvent(res.data)).catch(()=>{});
  },[id]);

  const toggle = (seat) => {
    setSelected(s => s.includes(seat) ? s.filter(x=>x!==seat) : [...s, seat]);
  };

  const book = async () => {
    const token = localStorage.getItem('token');
    if(!token){ alert('Please login'); nav('/login'); return; }
    try {
      const res = await axios.post('/api/bookings', { eventId: id, seatsRequested: selected.length, seatIds: selected }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      alert('Booked: ' + res.data.id);
      nav('/');
    } catch (err) {
      alert('Booking error');
    }
  };

  if(!event) return <div>Loading...</div>;
  const seats = ['A1','A2','A3','A4','A5'];
  return (<div>
    <h2>{event.title} - {event.venue}</h2>
    <div>
      {seats.map(s=> <button key={s} onClick={()=>toggle(s)} style={{margin:5}}>{selected.includes(s)?'✓ ':''}{s}</button>)}
    </div>
    <button onClick={book}>Confirm Booking ({selected.length})</button>
  </div>);
}
