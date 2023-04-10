// // import logo from './logo.svg';
// import './App.css';
// import NavBar from './components/NavBar';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import AudioUploader from './components/AudioUploader';
// // import { BrowserRouter as Router, Route } from 'react-router-dom';
// import { BrowserRouter } from 'react-router-dom';
// // import { BrowserRouter as Router, Route } from 'react-router-dom';

// function App() {
//   return (
//     <BrowserRouter>
//       <div className="App">
//         <header className="App-header">

        
//             <NavBar />
           
         
//           <h2>
//             Melody Master
//           </h2>
//           <p>
//             Unleash your inner star and master your favorite songs!
//           </p>
          
//         </header>
//       </div>
//       </BrowserRouter>
//   );
// }

// export default App;

// import './App.css';
// import NavBar from './components/NavBar';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import AudioUploader from './components/AudioUploader';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';

// function App() {
//   return (
//     <BrowserRouter>
//       <div className="App">
//         <header className="App-header">
//           <NavBar />
//           <h2>
//             Melody Master
//           </h2>
//           <p>
//             Unleash your inner star and master your favorite songs!
//           </p>

//           {/* <AudioUploader /> */}
        
//         </header>
//         <Routes>
//           <Route path="/submit" element={<AudioUploader />} />
//         </Routes>
//       </div>
//     </BrowserRouter>
//   );
// }

// export default App;

// import './App.css';
// import NavBar from './components/NavBar';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import AudioUploader from './components/AudioUploader';
// // import { BrowserRouter} from 'react-router-dom';
// // import { Routes} from 'react-router-dom';
// // import { Route} from 'react-router-dom';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';

// function App() {
//   return (
//     <BrowserRouter>
//       <div className="App">
//       <NavBar />
//         <header className="App-header">
//           {/* <NavBar /> */}
//           <h2>
//             Melody Master
//           </h2>
//           <p>
//             Unleash your inner star and master your favorite songs!
//           </p>
//         </header>
//         <Routes>
//           <Route path="/submit" element={<AudioUploader />} />
//         </Routes>
//       </div>
//       </BrowserRouter>
//   );
// }

// export default App;
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import NavBar from './components/NavBar';
import AudioUploader from './components/AudioUploader';
import HomePage from './components/HomePage';

function App() {
  return (
    <BrowserRouter>
      <div className="App" style={{ backgroundColor: '#282c34', height: '100vh'}}>
        <NavBar />
        <HomePage />
        <Routes>
          <Route path="/submit" element={<AudioUploader />} />
          <Route path="/home" element={<HomePage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}



export default App;