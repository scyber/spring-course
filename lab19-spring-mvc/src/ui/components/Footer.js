import React from 'react'
import {Row, Container, Col, Navbar} from 'react-bootstrap'

class Footer extends React.Component {

    render() {
        let fullYear = new Date().getFullYear();
        return(
            <Navbar fixed="bottom" bg="primary" variant="dark">
                            <Container>
                                  <Row>
                                       <Col lg={12} className="text-center text-muted">
                                       <div>  {fullYear} - {fullYear + 1} All Rights Reserved </div>
                                       </Col>
                                  </Row>
                            </Container>
            </Navbar>

        );
    }
}
export default Footer