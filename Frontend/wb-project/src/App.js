import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './App.css';
import HomePage from './pages/HomePage';
import RootLayout from './pages/RootLayout';
import VenueDaejeonW from './components/Venue/VenueDaejeonW';
import VenueHanhwa from './components/Venue/VenueHanhwa';
import UploadForm from './components/Upload/UploadForm';
import SignInForm from './components/SignIn/SignInForm';

const router = createBrowserRouter([
  {
    path: '/',
    element: <RootLayout />,
    children: [
      { path: '/', element: <HomePage /> },
      { path: '/daejeon-worldcup', element: <VenueDaejeonW /> },
      { path: '/hanwha-eagles', element: <VenueHanhwa /> },
      { path: '/review-test', element: <UploadForm /> },
      { path: '/signin-test', element: <SignInForm /> },
    ],
  },
])

function App() {
  return (
    <div>
      <RouterProvider router={router} />
    </div>
  );
}

export default App;