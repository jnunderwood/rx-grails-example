package client

import grails.rx.web.RxController
import grails.validation.ValidationException
import groovy.transform.CompileStatic

import static org.springframework.http.HttpStatus.*
import static rx.Observable.*
import grails.rx.web.*

@CompileStatic
class EmployeeController implements RxController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        zip( Employee.list(params), Employee.count() ) { List employeeList, Number count ->
            rx.render view:"index", model:[employeeList: employeeList, employeeCount: count]
        }
    }

    /*
    // original generated action
    def show() {
        Employee.get((Serializable)params.id)
    }
    */

    def show() {
        rx.Observable<Employee> observable = Employee.get((Serializable) params.id)
        Employee employee = observable.toBlocking().first()
        log.debug "employee: ${employee}"
        return observable
        // rx.respond observable
    }
}
