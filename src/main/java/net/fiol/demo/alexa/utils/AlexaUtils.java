package net.fiol.demo.alexa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.StandardCard;

public final class AlexaUtils {
	protected static final String SESSION_CONVERSATION_FLAG = "conversation";
	
	public static final String SamplesHelpText = "Here are some things you can say: Tell me something about a random year.  Or, what happened in nineteen eighty-nine?";
	public static final String RepromptText = "What else can I tell you?  Say \"Help\" for some suggestions.";
	
	private AlexaUtils() {
	}
	
	
	public static Card newCard( String cardTitle, String cardText ) {
		
		StandardCard card = new StandardCard();
		card.setTitle( (cardTitle == null) ? "MyDemoApp" : cardTitle );
		card.setText(cardText);

		/*
		Image cardImage = new Image();
		cardImage.setSmallImageUrl("https://www.cutlerstew.com/static/images/cutlerstew-720x480.png");
		cardImage.setLargeImageUrl("https://www.cutlerstew.com/static/images/cutlerstew-1200x800.png");
		
		card.setImage(cardImage);
		*/

		return card;
	}
	
	public static PlainTextOutputSpeech newSpeech( String speechText, boolean appendRepromptText ) {
		
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText( appendRepromptText ? speechText + "\n\n" + AlexaUtils.RepromptText : speechText);

		return speech;
	}
	
	public static SpeechletResponse newSpeechletResponse(Card card, PlainTextOutputSpeech speech, Session session, boolean shouldEndSession)  {
		
		// Say it...
		if ( AlexaUtils.inConversationMode(session) && !shouldEndSession) {
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(AlexaUtils.RepromptText);
			
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);
			
			return SpeechletResponse.newAskResponse(speech, reprompt, card);
		}
		else {		
			return SpeechletResponse.newTellResponse(speech, card);
		}
	}

	

	public static String spokenDayOfWeek( Date date, TimeZone zone ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		sdf.setTimeZone(zone);
		
		return sdf.format(date);
	}
	
	public static String spokenDate( Date date, TimeZone zone ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM d");
		sdf.setTimeZone(zone);
		
		return sdf.format(date);
	}
	
	public static String spokenTime( Date date, TimeZone zone ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		sdf.setTimeZone(zone);
		
		return sdf.format(date);
	}
	
	
	public static void setConversationMode(Session session, boolean conversationMode) {		
		if ( conversationMode )
			session.setAttribute(SESSION_CONVERSATION_FLAG, "true");
		else
			session.removeAttribute(SESSION_CONVERSATION_FLAG);
	}

	public static boolean inConversationMode(Session session) {
		 return session.getAttribute(SESSION_CONVERSATION_FLAG) != null;
	}	
	
	public static int randomInt(int min, int max) 
	{
		Random r = new Random( System.currentTimeMillis() );
		return r.nextInt((max - min) + 1) + min;
	}

}
