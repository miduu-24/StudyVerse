import React, { useEffect, useState } from 'react';

function LessonsList() {
  const [lessons, setLessons] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/lessons') // adresa backendului tău
      .then((res) => res.json())
      .then((data) => setLessons(data))
      .catch((err) => console.error('Eroare la preluare lecții:', err));
  }, []);

  return (
    <div>
      <h2>Lecții disponibile</h2>
      {lessons.map((lesson) => (
        <div key={lesson.id} style={{ border: '1px solid #ccc', marginBottom: '1rem', padding: '1rem' }}>
          <h3>{lesson.title}</h3>
          <p>{lesson.description}</p>
          <p><strong>Dificultate:</strong> {lesson.difficultyLevel}</p>
          {lesson.imageUrl && <img src={lesson.imageUrl} alt={lesson.title} width="200" />}
        </div>
      ))}
    </div>
  );
}

export default LessonsList;
