package net.fiol.demo.alexa.handlers;

import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import net.fiol.demo.alexa.utils.AlexaUtils;

@Component
public class AmazonCancelIntentHandler implements IntentHandler {

	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		
		String speechText= "OK. Goodbye";
		
		Card card = AlexaUtils.newCard("See ya later...", speechText);
		PlainTextOutputSpeech speech = AlexaUtils.newSpeech(speechText, false);
		
		return AlexaUtils.newSpeechletResponse(card, speech, session, true);
	}

}
