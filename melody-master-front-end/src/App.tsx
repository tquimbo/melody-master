// import logo from './logo.svg';
import './App.css';
import NavBar from './components/NavBar';
import 'bootstrap/dist/css/bootstrap.min.css';
import AudioUploader from './components/AudioUploader';

function App() {
  return (
    <div className="App"  >
      
      <header className="App-header" >
      <NavBar />
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        <h2>
          Melody Master
        </h2>
        <p>
          Unleash your inner star and master your favorite songs!
        </p>
      {/* <NavBar /> */}
      {/* <AudioUploader /> */}
        {/* <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a> */}
        {/* <AudioUploader /> */}
      </header>
      
    </div>
  );
}

export default App;
