class CardGame{

	public CardGame createCardGame(GameType type){
		if (type = GameType.BlackJack){
			return new BlackJack();
		}
		if (type == GameType.Poker){
			return new Poker();
		}

		return null;
	}
}

// factory offers an interface for creating an instance of a class, whith its subclass 
// decding which class to instantiate. 
