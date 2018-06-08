package server

import groovy.util.logging.Slf4j
import io.reactivex.Maybe
import io.reactivex.Single
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.HttpStatus

@Slf4j
@Controller("/employees")
class EmployeeController {

    private static final List<Employee> EMPLOYEES = [
        new Employee(name: "zero"), new Employee(name: "one"), new Employee(name: "two")
    ]

    @Get("/")
    Single<List<Employee>> index() {
        log.info "EMPLOYEES: ${EMPLOYEES}"
        return Single.just(EMPLOYEES)
    }

    @Get("/{id}")
    Maybe<Employee> show(def id) {
        def employee = EMPLOYEES[id as int]
        if (employee != null) {
            log.info "employee: ${employee}"
            return Maybe.just(employee)
        }
        log.info "employee: empty"
        return Maybe.empty()
    }
}
