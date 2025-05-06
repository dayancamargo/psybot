import React, { useState } from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
import PatientForm from "./patient-form";

// CreatePatient Component
const CreatePatient = () => {
    const navigate = useNavigate();

    const [formValues] =
        useState(
            {
                name: 'batata',
                email: 'batata@email.com',
                phoneNumber: '12345678901'
            })
    // onSubmit handler
    const onSubmit =
        PatientObject => {
            axios.post('http://localhost:8888/patient',
                JSON.stringify(PatientObject),
                {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .then(res => {
                    if (res.status === 200) {
                        alert('Patient successfully created')
                        navigate("/");
                    } else {
                        Promise.reject()
                    }

                }).catch(err => alert('Something went wrong' + err))
        }

    // Return Patient form
    return (
        <PatientForm initialValues={formValues} onSubmit={onSubmit} enableReinitialize>
            Create Patient
        </PatientForm>
    )
}

// Export CreatePatient Component
export default CreatePatient