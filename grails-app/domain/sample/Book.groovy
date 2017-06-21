package sample

import grails.gorm.MultiTenant

class Book implements MultiTenant<Book> {
    String tenantId
    String title
}
