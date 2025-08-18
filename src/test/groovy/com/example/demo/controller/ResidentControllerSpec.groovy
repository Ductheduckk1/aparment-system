package com.example.demo.controller

import com.example.demo.model.Resident
import com.example.demo.service.ResidentService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class ResidentControllerSpec extends Specification {

    def residentService = Mock(ResidentService)
    def residentController = new ResidentController(residentService)

    def "createResident should create new resident successfully"() {
        given:
        def request = new Resident(
                id: 1,
                name: "John Doe",
                email: "john@gmail.com",
                phone: "0123456789",
                idNumber: "ID123456",
                dob: "1990-01-01",
                gender: "Male"
        )
        residentService.createResident(request) >> request

        when:
        def response = residentController.createResident(request)

        then:
        response.statusCode == HttpStatus.OK
        response.body.id == 1
        response.body.name == "John Doe"
        response.body.email == "john@gmail.com"
    }

    def "updateResident should update existing resident successfully"() {
        given:
        def id = 1
        def request = new Resident(
                id: 1,
                name: "Updated Name",
                email: "updated@gmail.com",
                phone: "0999888777",
                idNumber: "ID123456",
                dob: "1991-02-02",
                gender: "Female"
        )
        residentService.update(request, id) >> request

        when:
        def response = residentController.updateResident(request, id)

        then:
        response.statusCode == HttpStatus.OK
        response.body.name == "Updated Name"
        response.body.gender == "Female"
        response.body.email == "updated@gmail.com"
    }

    def "getResidentById should return resident when found"() {
        given:
        def id = 1
        def resident = new Resident(
                id: 1,
                name: "Test Resident",
                email: "test@gmail.com",
                phone: "0333333333",
                idNumber: "ID000111",
                dob: "1985-05-05",
                gender: "Male"
        )
        residentService.getResidentById(id) >> resident

        when:
        def response = residentController.getResidentById(id)

        then:
        response.statusCode == HttpStatus.OK
        response.body.id == 1
        response.body.name == "Test Resident"
        response.body.email == "test@gmail.com"
    }

    def "getAllResidents should return list of residents"() {
        given:
        def residents = [
                new Resident(id: 1, name: "Alice", email: "alice@gmail.com", phone: "011", idNumber: "ID111", dob: "1992-01-01", gender: "Female"),
                new Resident(id: 2, name: "Bob", email: "bob@gmail.com", phone: "022", idNumber: "ID222", dob: "1988-02-02", gender: "Male")
        ]
        residentService.getAllResident() >> residents

        when:
        def response = residentController.getAllResidents()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 2
        response.body[0].name == "Alice"
        response.body[1].name == "Bob"
    }

    def "deleteResident should delete successfully"() {
        given:
        def id = 1
        residentService.deleteResident(id) >> {}

        when:
        def response = residentController.deleteResident(id)

        then:
        response.statusCode == HttpStatus.NO_CONTENT
        !response.hasBody()
    }
}
