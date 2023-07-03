package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement transferHead = $("[data-test-id='dashboard']");
    private SelenideElement transferAmount = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    public TransferPage() {
        transferHead.shouldBe(visible);
    }

    public void makeTransfer(String amountForTransfer, DataHelper.CardInfo cardInfo) {
        transferAmount.setValue(amountForTransfer);
        fromField.setValue(cardInfo.getCardNumber());
        transferButton.click();
    }

    public DashboardPage makeValidTransfer(String amountForTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountForTransfer, cardInfo);
        return new DashboardPage();
    }
}