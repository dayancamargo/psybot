import './App.css';

// Import Bootstrap
import "bootstrap/dist/css/bootstrap.min.css";
import { Route, Routes } from "react-router-dom";
import AppHeader from "./Components/commons/AppHeader";
import ListPatient from './Components/patient/patient-list.components';
import EditPatient from './Components/patient/patient-edit.components';
import CreatePatient from './Components/patient/patient-create.components';

// App Component
const App = () => {
    return (
        <div className="App">
            <AppHeader />
            <Routes>
                {/* Patient urls */}
                <Route path="/" element={<ListPatient />} /> 
                <Route path="/patient/" element={<CreatePatient />} />
                <Route path="/patient-edit/:id" element={<EditPatient />} />

            </Routes>
       </div>
    );
};

export default App;