import React from 'react';
import { Button, FloatingLabel, FormControl, Form } from 'react-bootstrap';
// import { Form } from 'react-bootstrap/Form';

const PatientForm = (props) => {

    const [formValues] =
        useState(
            {
                name: 'batata',
                email: 'batata@email.com',
                phoneNumber: '12345678901'
            })


    return (
        <div className="mb-3">
            <Form>
                {/* <FormGroup>
                    <FormLabel>Name</FormLabel>
                    <Field name="name" type="text" className="form-control" />
                    <ErrorMessage name="name" className="d-block invalid-feedback" component="span" />
                </FormGroup> */}
                <FloatingLabel controlId="name" label="Name">
                    <FormControl type="text" />
                </FloatingLabel>
                <FloatingLabel controlId="floatingInput" label="Email address" className="mb-3" >
                    <Form.Control type="email" placeholder="name@example.com" />
                </FloatingLabel>

                {/* <FormGroup>
                    <FormLabel>Email address</FormLabel>
                    <Field name="email" type="email" className="form-control" />
                    <ErrorMessage name="email" className="d-block invalid-feedback" component="span" />
                </FormGroup>
                <FormGroup>
                    <FormLabel>Phone number</FormLabel>
                    <Field name="phoneNumber" type="number" className="form-control" />
                    <ErrorMessage name="phoneNumber" className="d-block invalid-feedback" component="span" />
                    <small id="phoneNumberHelp" className="text-muted">Only numbers.</small>
                </FormGroup> */}
                <Button variant="primary" size="lg" block="block" type="submit" className="form-control">
                    {props.children}
                </Button>
            </Form>
        </div>
    );
};

export default PatientForm;