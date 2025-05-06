import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const ListPatient = () => {

    const [patients, setPatient] = useState([]);

    useEffect(() => {
        getPatientData();
    }, []);


    const getPatientData = () => {
        axios
            .get("http://localhost:8888/patient")
            .then((res) => {
                setPatient(res.data);
            })
            .catch((err) => {
                console.log(err);
            });
    };

    if (patients.length < 0) {
        return <h1>no user found</h1>;
    }
    else {
        return (
            <div className="table-wrapper">
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {patients?.map((item, i) => {
                            return (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.email}</td>
                                    <td>{item.phoneNumber}</td>
                                    <td>
                                        <Link to={`/patient-edit/${item.id}`}>
                                            <i className="fa fa-pencil" aria-hidden="true"></i>
                                        </Link>

                                        {/* <i
                                            className="fa fa-trash-o"
                                            aria-hidden="true"
                                            onClick={() => handelDelete(item.id)}
                                        /> */}
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        );
    }
}


// Export ListPatient Component
export default ListPatient