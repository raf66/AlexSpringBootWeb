package net.fiol.demo.alexa.handlers;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import net.fiol.demo.alexa.utils.AlexaUtils;
import net.fiol.demo.models.NumberTrivia;
import net.fiol.demo.services.NumbersAPIService;

@Component
public class RandomYearIntentHandler implements IntentHandler {
	protected Logger logger = LoggerFactory.getLogger(RandomYearIntentHandler.class);
	
	@Autowired
	private NumbersAPIService numbersService;
	
	
	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		
		// Get some trivia about a random year between 1900 and today.
		int year = AlexaUtils.randomInt(1900, LocalDate.now().getYear());		
		NumberTrivia trivia = numbersService.getYearTrivia(year);
		
		Card card = AlexaUtils.newCard("Random Trivia", trivia.getText());
		PlainTextOutputSpeech speech = AlexaUtils.newSpeech(trivia.getText(), AlexaUtils.inConversationMode(session));

		return AlexaUtils.newSpeechletResponse( card, speech, session, false);
	}

}
