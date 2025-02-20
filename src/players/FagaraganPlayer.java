package players;

import game.HandRanks;
import game.Player;

public class FagaraganPlayer extends Player {
    /**
     * Constructs a new Player with the specified name.
     * Initializes the player's hand, bank balance, and various status flags.
     *
     * @param name The name of the player.
     */
    public FagaraganPlayer(String name) {
        super(name);
    }

    @Override
    protected void takePlayerTurn() {
        if(shouldFold()) {
            fold();
        } else if(shouldCheck()) {
            check();
        } else if(shouldCall()) {
            call();
        } else if(shouldRaise()) {
            raise(getGameState().getTableMinBet());
        }
    }
    // this avoids the player raising or going all-in

    @Override
    protected boolean shouldFold() {
        HandRanks currentHandRank = evaluatePlayerHand();
        return currentHandRank == HandRanks.HIGH_CARD || getGameState().getTableBet() > getBank()*0.2;
    }

    @Override
    protected boolean shouldCheck() {
        return !getGameState().isActiveBet();
    }

    @Override
    protected boolean shouldCall() {
        HandRanks currentHandRanks = evaluatePlayerHand();
        return getGameState().getTableBet() <= getBank() * 0.3;
    }

    @Override
    protected boolean shouldRaise() {
        HandRanks currentHandRanks = evaluatePlayerHand();
        return evaluatePlayerHand().compareTo(HandRanks.PAIR) >= 0 && getGameState().getTableBet() <= getBank() * 0.4;
    }

    @Override
    protected boolean shouldAllIn() {
        return false;
    }
}
