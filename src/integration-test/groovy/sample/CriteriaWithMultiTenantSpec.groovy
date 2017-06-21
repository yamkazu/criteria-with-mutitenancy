package sample

import grails.gorm.multitenancy.Tenants
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Rollback
@Integration
class CriteriaWithMultiTenantSpec extends Specification {

    def test() {
        given:
        def firstBook = Tenants.withId('first') { new Book(title: 'sample book').save(flush: true) }
        def secondBook = Tenants.withId('second') { new Book(title: 'sample book').save(flush: true) }

        when:
        def books = Tenants.withId('first') {
            Book.withCriteria { eq 'title', 'sample book' }
        }

        then:
        books == [firstBook] //=> actual: [firstBook, secondBook] !!!!
    }
}
