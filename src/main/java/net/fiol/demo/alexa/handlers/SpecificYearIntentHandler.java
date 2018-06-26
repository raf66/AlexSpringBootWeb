package net.fiol.demo.alexa.handlers;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import net.fiol.demo.alexa.utils.AlexaUtils;
import net.fiol.demo.models.NumberTrivia;
import net.fiol.demo.services.NumbersAPIService;

@Component
public class SpecificYearIntentHandler implements IntentHandler {
	protected Logger logger = LoggerFactory.getLogger(SpecificYearIntentHandler.class);
	
	@Autowired
	private NumbersAPIService numbersService;

	
	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		
		StringBuffer speechText = new StringBuffer();
		
		
		// Get the Talent slot		
		Slot yearSlot = intent.getSlot("Year");
		String yearStr = yearSlot == null ? null : StringUtils.trimToNull(yearSlot.getValue());
		
		if ( yearStr != null ) {
			
			if ( logger.isInfoEnabled())
				logger.info("Got year slot value = '" + yearStr + "'.");
			
			try
			{
				// parse the year as an Integer and lookup trivia
				int year = Integer.parseInt(yearStr);
				NumberTrivia trivia = numbersService.getYearTrivia(year);
				
				speechText.append( trivia.getText() );
			}
			catch (NumberFormatException e)
			{
				speechText.append("I do not understand what you mean by " + yearStr + ".  Please say a year.");
			}			
			
		}
		else {
			speechText.append("I didn't hear which year.  Please say something like \"Tell me something about the year nineteen eighty-four.\"");
		}
		
		Card card = AlexaUtils.newCard("Trivia", speechText.toString());
		PlainTextOutputSpeech speech = AlexaUtils.newSpeech(speechText.toString(), AlexaUtils.inConversationMode(session));

		return AlexaUtils.newSpeechletResponse( card, speech, session, false);
	}

}
