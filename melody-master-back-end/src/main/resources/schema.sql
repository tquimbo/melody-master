CREATE TABLE audio_file (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    artist VARCHAR(255),
    genre VARCHAR(255),
    audio_url VARCHAR(255),
    user_recording_id BIGINT,
    note_id BIGINT,
    lyrics_id BIGINT,
    FOREIGN KEY (user_recording_id) REFERENCES user_recording(id),
    FOREIGN KEY (note_id) REFERENCES note(id),
    FOREIGN KEY (lyrics_id) REFERENCES lyrics(id)
);

CREATE TABLE lyrics (
    id BIGSERIAL PRIMARY KEY,
    song_id BIGINT,
    audio_file_id BIGINT,
    content TEXT,
    FOREIGN KEY (song_id) REFERENCES song(id),
    FOREIGN KEY (audio_file_id) REFERENCES audio_file(id)
);

CREATE TABLE notes (
    id BIGSERIAL PRIMARY KEY,
    pitch REAL,
    start_time DOUBLE PRECISION,
    end_time DOUBLE PRECISION,
    duration DOUBLE PRECISION,
    lyrics TEXT,
    song_id BIGINT,
    audio_file_id BIGINT,
    FOREIGN KEY (song_id) REFERENCES song(id),
    FOREIGN KEY (audio_file_id) REFERENCES audio_file(id)
);

CREATE TABLE scores (
    id SERIAL PRIMARY KEY,
    user_recording_id INT,
    song_id INT,
    accuracy_score DOUBLE PRECISION,
    timing_score DOUBLE PRECISION,
    total_score DOUBLE PRECISION,
    FOREIGN KEY (user_recording_id) REFERENCES user_recording(id),
    FOREIGN KEY (song_id) REFERENCES songs(id)
);

CREATE TABLE songs (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    artist VARCHAR(255),
    album VARCHAR(255),
    year INT,
    genre VARCHAR(255),
    user_recording_id BIGINT,
    note_id BIGINT,
    lyrics_id BIGINT,
    FOREIGN KEY (user_recording_id) REFERENCES user_recording(id),
    FOREIGN KEY (note_id) REFERENCES notes(id),
    FOREIGN KEY (lyrics_id) REFERENCES lyrics(id)
);

CREATE TABLE song_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    song_id BIGINT,
    played_at TIMESTAMP WITHOUT TIME ZONE,
    score DOUBLE PRECISION,
    FOREIGN KEY (user_id) REFERENCES users(id),  -- Assuming a 'users' table exists
    FOREIGN KEY (song_id) REFERENCES songs(id)  -- Assuming a 'songs' table exists
);

CREATE TABLE synthesizer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    manufacturer VARCHAR(255),
    model VARCHAR(255)
);