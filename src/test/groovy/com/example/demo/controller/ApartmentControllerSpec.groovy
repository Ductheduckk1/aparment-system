package com.example.demo.controller

import com.example.demo.model.Apartment
import com.example.demo.service.ApartmentService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class ApartmentControllerSpec extends Specification {

    def apartmentService = Mock(ApartmentService)
    def apartmentController = new AparmentController(apartmentService)

    def "createApartment should create new apartment successfully"() {
        given:
        def request = new Apartment(
                id: 1,
                area: 55.5f,
                numRooms: 2,
                phone: "0123456789",
                email: "apt@gmail.com",
                note: "Nice apartment"
        )
        apartmentService.createApartment(request) >> request

        when:
        def response = apartmentController.createApartment(request)

        then:
        response.statusCode == HttpStatus.OK
        response.body.id == 1
        response.body.area == 55.5f
        response.body.phone == "0123456789"
    }

    def "updateApartment should update existing apartment successfully"() {
        given:
        def id = 1
        def request = new Apartment(
                id: 1,
                area: 70.0f,
                numRooms: 3,
                phone: "0999888777",
                email: "update@gmail.com",
                note: "Updated"
        )
        apartmentService.update(request, id) >> request

        when:
        def response = apartmentController.updateApartment(request, id)

        then:
        response.statusCode == HttpStatus.OK
        response.body.area == 70.0f
        response.body.numRooms == 3
        response.body.email == "update@gmail.com"
    }

    def "getApartmentById should return apartment when found"() {
        given:
        def id = 1
        def apartment = new Apartment(
                id: 1,
                area: 60.0f,
                numRooms: 2,
                phone: "0333333333",
                email: "test@gmail.com",
                note: "Test note"
        )
        apartmentService.getApartmentById(id) >> apartment

        when:
        def response = apartmentController.getApartments(id)

        then:
        response.statusCode == HttpStatus.OK
        response.body.id == 1
        response.body.area == 60.0f
        response.body.email == "test@gmail.com"
    }

    def "getAllApartments should return list of apartments"() {
        given:
        def apartments = [
                new Apartment(id: 1, area: 45.0f, numRooms: 1, phone: "011", email: "a1@gmail.com", note: "A1"),
                new Apartment(id: 2, area: 80.0f, numRooms: 3, phone: "022", email: "a2@gmail.com", note: "A2")
        ]
        apartmentService.getAllAparment() >> apartments

        when:
        def response = apartmentController.getAllApartments()

        then:
        response.statusCode == HttpStatus.OK
        response.body.size() == 2
        response.body[0].email == "a1@gmail.com"
        response.body[1].email == "a2@gmail.com"
    }

    def "deleteApartment should delete successfully"() {
        given:
        def id = 1
        apartmentService.deleteApartment(id) >> {}

        when:
        def response = apartmentController.deleteApartment(id)

        then:
        response.statusCode == HttpStatus.NO_CONTENT
        !response.hasBody()
    }
}
