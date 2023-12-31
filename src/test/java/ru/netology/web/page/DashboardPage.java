package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    private String balanceStart = "баланс: ";
    private String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage cardToTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id",cardInfo.getTestID())).$("button").click();
        return new TransferPage();
    }

    public int getCardBalance(int index){
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}