package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    DashboardPage dashboardPage;
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);

        var cardNumberOne = DataHelper.getFirstCardInfo();
        var balanceCardOne = dashboardPage.getCardBalance(0);
        var cardNumberTwo = DataHelper.getSecondCardInfo();
        var balanceCardTwo = dashboardPage.getCardBalance(1);
        var amount = DataHelper.generateValidAmount(balanceCardOne);
        var newBalanceOfCardOne = balanceCardOne - amount;
        var newBalanceOfCardTwo = balanceCardTwo + amount;
        var transferPage = dashboardPage.cardToTransfer(cardNumberTwo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), cardNumberOne);
        var actualBalanceCardOne = dashboardPage.getCardBalance(0);
        var actualBalanceCardTwo = dashboardPage.getCardBalance(1);
        assertEquals(newBalanceOfCardOne, actualBalanceCardOne);
        assertEquals(newBalanceOfCardTwo, actualBalanceCardTwo);
    }
}