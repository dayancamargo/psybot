// EditPatient Component for update Patient data
import React, { useState, useEffect } from "react";
import axios from "axios";
import PatientForm from "./patient-form";
import { useParams, useNavigate } from "react-router-dom";

// EditPatient Component
const EditPatient = () => {
    const [formValues, setFormValues] =
        useState(
            {
                id: "",
                name: "",
                email: "",
                phoneNumber: "",
            }
        );

    const { id } = useParams();
    const navigate = useNavigate();

    // Load data from server and reinitialize Patient form
    useEffect(() => {
        axios
            .get("http://localhost:8888/patient/" + id)
            .then((res) => {
                const {
                    id,
                    name,
                    email,
                    phoneNumber
                } = res.data;
                setFormValues(
                    {
                        id,
                        name,
                        email,
                        phoneNumber
                    });
            })
            .catch((err) => console.log(err));
    }, []);

    //onSubmit handler

    const onSubmit = (PatientObject) => {
        axios
            .put("http://localhost:8888/patient/" + id,
                JSON.stringify(PatientObject),
                {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
            )
            .then((res) => {
                if (res.status === 200) {
                    alert("Patient successfully updated");
                    navigate("/");
                } else Promise.reject();
            })
            .catch(
                (err) => {
                    alert("Something went wrong - " + err)
                }
            );
    };

    // Return Patient form
    return (
        <PatientForm initialValues={formValues} onSubmit={onSubmit} enableReinitialize>
            Update Patient
        </PatientForm>
    );
};

// Export EditPatient Component
export default EditPatient;
