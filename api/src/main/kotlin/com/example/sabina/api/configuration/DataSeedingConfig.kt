package com.example.sabina.api.configuration

import com.example.sabina.api.models.*
import com.example.sabina.api.repositories.AccountRepository
import com.example.sabina.api.repositories.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author Sabina Muhic
 */
@Configuration
class DataSeedingConfig {

    private val passEncoder = BCryptPasswordEncoder()

    @Bean
    fun seedData(userRepository: UserRepository, accountRepository: AccountRepository): CommandLineRunner {
        return CommandLineRunner {
            val user1 = User(id = 1L, username = "user1", firstName = "John", lastName = "Doe", password = passEncoder.encode("password"), address = "Address 1", city = "Zenica", country = "BiH", phoneNumber = "584205249", email = "user1@email.com", enabled = true)
            val user2 = User(id = 2L, username = "user2", firstName = "Chris", lastName = "Davis", password = passEncoder.encode("password"), address = "Address 2", city = "Bihać", country = "Croatia", phoneNumber = "759501009", email = "user2@domain.org", enabled = true)
            val user3 = User(id = 3L, username = "user3", firstName = "Olivia", lastName = "Smith", password = passEncoder.encode("password"), address = "Address 3", city = "Bijeljina", country = "Croatia", phoneNumber = "387940943", email = "user3@mail.net", enabled = true)
            val user4 = User(id = 4L, username = "user4", firstName = "Emma", lastName = "Davis", password = passEncoder.encode("password"), address = "Address 4", city = "Bihać", country = "BiH", phoneNumber = "433389682", email = "user4@email.com", enabled = true)
            val user5 = User(id = 5L, username = "user5", firstName = "Emily", lastName = "Williams", password = passEncoder.encode("password"), address = "Address 5", city = "Sarajevo", country = "Croatia", phoneNumber = "096814454", email = "user5@email.com", enabled = true)
            val user6 = User(id = 6L, username = "user6", firstName = "Daniel", lastName = "Martinez", password = passEncoder.encode("password"), address = "Address 6", city = "Brčko", country = "Slovenia", phoneNumber = "661176653", email = "user6@test.com", enabled = true)
            val user7 = User(id = 7L, username = "user7", firstName = "Daniel", lastName = "Garcia", password = passEncoder.encode("password"), address = "Address 7", city = "Trebinje", country = "Slovenia", phoneNumber = "546305737", email = "user7@site.biz", enabled = true)
            val user8 = User(id = 8L, username = "user8", firstName = "Michael", lastName = "Johnson", password = passEncoder.encode("password"), address = "Address 8", city = "Bijeljina", country = "Montenegro", phoneNumber = "216835788", email = "user8@test.com", enabled = true)
            val user9 = User(id = 9L, username = "user9", firstName = "Olivia", lastName = "Martinez", password = passEncoder.encode("password"), address = "Address 9", city = "Mostar", country = "Serbia", phoneNumber = "298456930", email = "user9@site.biz", enabled = true)
            val user10 = User(id = 10L, username = "user10", firstName = "Sophia", lastName = "Miller", password = passEncoder.encode("password"), address = "Address 10", city = "Zenica", country = "Macedonia", phoneNumber = "821378598", email = "user10@mail.net", enabled = true)

            val users = listOf(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10)
//                User(id = null, username = "user11", firstName = "John", lastName = "Garcia", password = "password", address = "Address 11", city = "Tuzla", country = "Serbia", phoneNumber = "410152421", email = "user11@domain.org", enabled = true),
//                User(id = null, username = "user12", firstName = "Emily", lastName = "Brown", password = "password", address = "Address 12", city = "Doboj", country = "BiH", phoneNumber = "176374493", email = "user12@site.biz", enabled = true),
//                User(id = null, username = "user13", firstName = "David", lastName = "Smith", password = "password", address = "Address 13", city = "Sarajevo", country = "Montenegro", phoneNumber = "159407448", email = "user13@test.com", enabled = true),
//                User(id = null, username = "user14", firstName = "Emma", lastName = "Jones", password = "password", address = "Address 14", city = "Zenica", country = "Montenegro", phoneNumber = "474220388", email = "user14@domain.org", enabled = true),
//                User(id = null, username = "user15", firstName = "Daniel", lastName = "Doe", password = "password", address = "Address 15", city = "Doboj", country = "BiH", phoneNumber = "673848001", email = "user15@domain.org", enabled = true),
//                User(id = null, username = "user16", firstName = "Chris", lastName = "Martinez", password = "password", address = "Address 16", city = "Doboj", country = "BiH", phoneNumber = "618586932", email = "user16@mail.net", enabled = true),
//                User(id = null, username = "user17", firstName = "Michael", lastName = "Smith", password = "password", address = "Address 17", city = "Mostar", country = "Serbia", phoneNumber = "107912776", email = "user17@mail.net", enabled = true),
//                User(id = null, username = "user18", firstName = "David", lastName = "Williams", password = "password", address = "Address 18", city = "Trebinje", country = "BiH", phoneNumber = "824504513", email = "user18@test.com", enabled = true),
//                User(id = null, username = "user19", firstName = "Sophia", lastName = "Doe", password = "password", address = "Address 19", city = "Bijeljina", country = "Montenegro", phoneNumber = "054698228", email = "user19@test.com", enabled = true),
//                User(id = null, username = "user20", firstName = "Jane", lastName = "Jones", password = "password", address = "Address 20", city = "Doboj", country = "Macedonia", phoneNumber = "707351515", email = "user20@site.biz", enabled = true),
//                User(id = null, username = "user21", firstName = "Chris", lastName = "Jones", password = "password", address = "Address 21", city = "Bijeljina", country = "Montenegro", phoneNumber = "795307533", email = "user21@test.com", enabled = true),
//                User(id = null, username = "user22", firstName = "Emily", lastName = "Miller", password = "password", address = "Address 22", city = "Bihać", country = "Serbia", phoneNumber = "465673597", email = "user22@test.com", enabled = true),
//                User(id = null, username = "user23", firstName = "Sophia", lastName = "Martinez", password = "password", address = "Address 23", city = "Mostar", country = "Macedonia", phoneNumber = "418519688", email = "user23@domain.org", enabled = true),
//                User(id = null, username = "user24", firstName = "John", lastName = "Jones", password = "password", address = "Address 24", city = "Sarajevo", country = "Macedonia", phoneNumber = "777943911", email = "user24@mail.net", enabled = true),
//                User(id = null, username = "user25", firstName = "Daniel", lastName = "Smith", password = "password", address = "Address 25", city = "Brčko", country = "BiH", phoneNumber = "987761086", email = "user25@test.com", enabled = true),
//                User(id = null, username = "user26", firstName = "Emma", lastName = "Brown", password = "password", address = "Address 26", city = "Doboj", country = "Croatia", phoneNumber = "197331841", email = "user26@domain.org", enabled = true),
//                User(id = null, username = "user27", firstName = "Daniel", lastName = "Davis", password = "password", address = "Address 27", city = "Mostar", country = "Croatia", phoneNumber = "596204348", email = "user27@domain.org", enabled = true),
//                User(id = null, username = "user28", firstName = "Sophia", lastName = "Garcia", password = "password", address = "Address 28", city = "Doboj", country = "Croatia", phoneNumber = "930030457", email = "user28@site.biz", enabled = true),
//                User(id = null, username = "user29", firstName = "Chris", lastName = "Garcia", password = "password", address = "Address 29", city = "Mostar", country = "Slovenia", phoneNumber = "169947892", email = "user29@site.biz", enabled = true),
//                User(id = null, username = "user30", firstName = "Sophia", lastName = "Garcia", password = "password", address = "Address 30", city = "Sarajevo", country = "Montenegro", phoneNumber = "221860724", email = "user30@mail.net", enabled = true),
//                User(id = null, username = "user31", firstName = "John", lastName = "Williams", password = "password", address = "Address 31", city = "Sarajevo", country = "Serbia", phoneNumber = "946942292", email = "user31@mail.net", enabled = true),
//                User(id = null, username = "user32", firstName = "Jane", lastName = "Garcia", password = "password", address = "Address 32", city = "Zenica", country = "Slovenia", phoneNumber = "067640014", email = "user32@domain.org", enabled = true),
//                User(id = null, username = "user33", firstName = "Sophia", lastName = "Garcia", password = "password", address = "Address 33", city = "Brčko", country = "Montenegro", phoneNumber = "648217543", email = "user33@domain.org", enabled = true),
//                User(id = null, username = "user34", firstName = "Jane", lastName = "Williams", password = "password", address = "Address 34", city = "Zenica", country = "Macedonia", phoneNumber = "346272127", email = "user34@domain.org", enabled = true),
//                User(id = null, username = "user35", firstName = "Chris", lastName = "Brown", password = "password", address = "Address 35", city = "Brčko", country = "Montenegro", phoneNumber = "894484514", email = "user35@mail.net", enabled = true),
//                User(id = null, username = "user36", firstName = "Michael", lastName = "Jones", password = "password", address = "Address 36", city = "Zenica", country = "Montenegro", phoneNumber = "307408303", email = "user36@domain.org", enabled = true),
//                User(id = null, username = "user37", firstName = "Emily", lastName = "Williams", password = "password", address = "Address 37", city = "Tuzla", country = "Serbia", phoneNumber = "643987923", email = "user37@domain.org", enabled = true),
//                User(id = null, username = "user38", firstName = "Daniel", lastName = "Smith", password = "password", address = "Address 38", city = "Trebinje", country = "Croatia", phoneNumber = "329713945", email = "user38@mail.net", enabled = true),
//                User(id = null, username = "user39", firstName = "Chris", lastName = "Miller", password = "password", address = "Address 39", city = "Brčko", country = "Croatia", phoneNumber = "777667981", email = "user39@test.com", enabled = true),
//                User(id = null, username = "user40", firstName = "Emily", lastName = "Doe", password = "password", address = "Address 40", city = "Bijeljina", country = "Montenegro", phoneNumber = "509227392", email = "user40@domain.org", enabled = true),
//                User(id = null, username = "user41", firstName = "John", lastName = "Martinez", password = "password", address = "Address 41", city = "Trebinje", country = "Croatia", phoneNumber = "989872272", email = "user41@mail.net", enabled = true),
//                User(id = null, username = "user42", firstName = "Jane", lastName = "Brown", password = "password", address = "Address 42", city = "Mostar", country = "Macedonia", phoneNumber = "951612295", email = "user42@domain.org", enabled = true),
//                User(id = null, username = "user43", firstName = "David", lastName = "Martinez", password = "password", address = "Address 43", city = "Sarajevo", country = "Serbia", phoneNumber = "242847747", email = "user43@domain.org", enabled = true),
//                User(id = null, username = "user44", firstName = "Emily", lastName = "Doe", password = "password", address = "Address 44", city = "Sarajevo", country = "Croatia", phoneNumber = "055156598", email = "user44@test.com", enabled = true),
//                User(id = null, username = "user45", firstName = "Michael", lastName = "Johnson", password = "password", address = "Address 45", city = "Trebinje", country = "Macedonia", phoneNumber = "153763084", email = "user45@domain.org", enabled = true),
//                User(id = null, username = "user46", firstName = "Sophia", lastName = "Brown", password = "password", address = "Address 46", city = "Zenica", country = "Macedonia", phoneNumber = "963604597", email = "user46@mail.net", enabled = true),
//                User(id = null, username = "user47", firstName = "David", lastName = "Miller", password = "password", address = "Address 47", city = "Trebinje", country = "Serbia", phoneNumber = "551116452", email = "user47@site.biz", enabled = true),
//                User(id = null, username = "user48", firstName = "Daniel", lastName = "Garcia", password = "password", address = "Address 48", city = "Bihać", country = "BiH", phoneNumber = "318297772", email = "user48@test.com", enabled = true),
//                User(id = null, username = "user49", firstName = "Jane", lastName = "Doe", password = "password", address = "Address 49", city = "Sarajevo", country = "Montenegro", phoneNumber = "617644730", email = "user49@test.com", enabled = true),
//                User(id = null, username = "user50", firstName = "Olivia", lastName = "Williams", password = "password", address = "Address 50", city = "Zenica", country = "Macedonia", phoneNumber = "089359519", email = "user50@test.com", enabled = true)

            userRepository.saveAll(users)

            val accounts = listOf(
                Account(id = null, accountNumber = "BA541023736127229849", type = AccountType.CURRENT, owner = user1, status = AccountStatus.ACTIVE, balance = 1200.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA171021946228423187", type = AccountType.SAVINGS, owner = user1, status = AccountStatus.ACTIVE, balance = 3000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA66102143488562761", type = AccountType.LOAN, owner = user1, status = AccountStatus.ACTIVE, balance = -5000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA921024247897312542", type = AccountType.CURRENT, owner = user2, status = AccountStatus.ACTIVE, balance = 1500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA311021627283763551", type = AccountType.SAVINGS, owner = user2, status = AccountStatus.ACTIVE, balance = 2500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA231022737926723241", type = AccountType.LOAN, owner = user2, status = AccountStatus.ACTIVE, balance = -2000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA711025255151997772", type = AccountType.CURRENT, owner = user3, status = AccountStatus.ACTIVE, balance = 1800.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA871025377232418946", type = AccountType.SAVINGS, owner = user3, status = AccountStatus.ACTIVE, balance = 2200.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA131025868788724968", type = AccountType.LOAN, owner = user3, status = AccountStatus.ACTIVE, balance = -3500.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA881025546785785378", type = AccountType.CURRENT, owner = user4, status = AccountStatus.ACTIVE, balance = 1000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA641028359719767125", type = AccountType.SAVINGS, owner = user4, status = AccountStatus.ACTIVE, balance = 5000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA471027136598557558", type = AccountType.LOAN, owner = user4, status = AccountStatus.ACTIVE, balance = -4500.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA591027811292771768", type = AccountType.CURRENT, owner = user5, status = AccountStatus.ACTIVE, balance = 500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA451028992255835588", type = AccountType.SAVINGS, owner = user5, status = AccountStatus.ACTIVE, balance = 1500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA961026831279249545", type = AccountType.LOAN, owner = user5, status = AccountStatus.ACTIVE, balance = -3000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA071021869821832991", type = AccountType.CURRENT, owner = user6, status = AccountStatus.ACTIVE, balance = 800.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA731025984475228942", type = AccountType.SAVINGS, owner = user6, status = AccountStatus.ACTIVE, balance = 3500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA261025345526596524", type = AccountType.LOAN, owner = user6, status = AccountStatus.ACTIVE, balance = -7000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA041024452999659616", type = AccountType.CURRENT, owner = user7, status = AccountStatus.ACTIVE, balance = 2000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA531022392617597759", type = AccountType.SAVINGS, owner = user7, status = AccountStatus.ACTIVE, balance = 2700.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA241025725839742999", type = AccountType.LOAN, owner = user7, status = AccountStatus.ACTIVE, balance = -8000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA551024254213845247", type = AccountType.CURRENT, owner = user8, status = AccountStatus.ACTIVE, balance = 3000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA671021164395888917", type = AccountType.SAVINGS, owner = user8, status = AccountStatus.ACTIVE, balance = 1000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA921028411889654921", type = AccountType.LOAN, owner = user8, status = AccountStatus.ACTIVE, balance = -1000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA731028314118565694", type = AccountType.CURRENT, owner = user9, status = AccountStatus.ACTIVE, balance = 4500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA151021762177858432", type = AccountType.SAVINGS, owner = user9, status = AccountStatus.ACTIVE, balance = 3000.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA981028839387598331", type = AccountType.LOAN, owner = user9, status = AccountStatus.ACTIVE, balance = -2000.0, currency = Currency.EUR),

                Account(id = null, accountNumber = "BA901026139979178524", type = AccountType.CURRENT, owner = user10, status = AccountStatus.ACTIVE, balance = 3500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA481025972958858511", type = AccountType.SAVINGS, owner = user10, status = AccountStatus.ACTIVE, balance = 2500.0, currency = Currency.EUR),
                Account(id = null, accountNumber = "BA311025179686678947", type = AccountType.LOAN, owner = user10, status = AccountStatus.ACTIVE, balance = -4000.0, currency = Currency.EUR)
            )
            accountRepository.saveAll(accounts)
        }
    }
}