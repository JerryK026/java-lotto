package lotto;

import java.util.List;
import java.util.Map;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.Winning;
import lotto.ui.InputView;
import lotto.ui.ResultView;

public class LottoGame {

  private final LottoMachine lottoMachine = new LottoMachine();
  private final InputView inputView = new InputView();
  private final ResultView resultView = new ResultView();

  public void play() {
    Money userMoney = inputView.buy();
    Money lottoPurchasablePrice = Money.toLottoPurchasablePrice(userMoney);
    resultView.printChange(userMoney.subtraction(lottoPurchasablePrice));
    resultView.printPurchaseAmount(lottoPurchasablePrice.ticketPurchasableNumber());

    LottoTickets tickets = lottoMachine.buy(lottoPurchasablePrice);
    resultView.showTicketsInfo(tickets);

    List<LottoNumber> lastWeekNumbers = inputView.lastWeekNumbers();
    Map<Winning, Integer> winnings = Winning.score(tickets, lastWeekNumbers);

    double profit = Winning.profit(winnings, lottoPurchasablePrice);
    resultView.printResult(winnings, profit);
  }
}
