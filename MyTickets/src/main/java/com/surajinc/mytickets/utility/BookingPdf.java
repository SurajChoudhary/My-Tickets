package com.surajinc.mytickets.utility;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.surajinc.mytickets.form.BookMovieForm;

public class BookingPdf extends AbstractPdfView{


	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document document,
			PdfWriter arg2, HttpServletRequest arg3, HttpServletResponse arg4)
			throws Exception {
		
		BookMovieForm form = (BookMovieForm) map.get("printInfo");
		
		
		Paragraph p1 = new Paragraph("MyTickets.com!");
		p1.getFont().setSize(16);
		p1.getFont().setColor(Color.BLUE);
		Paragraph p2 = new Paragraph("Your Booking Confirmation");
		p2.getFont().setSize(14);
		Paragraph p3 = new Paragraph("Movie Name :" + form.getMovieShowing().getMovie().getName());
		Paragraph p4 = new Paragraph("Theater :" + form.getCinemaName());
		Paragraph p5 = new Paragraph("Theater Address:" + form.getMovieShowing().getCinema().getAddress());
		Paragraph p6 = new Paragraph("Number of Ticket :" + form.getNumberOfTickets());
		Paragraph p7 = new Paragraph("Ticket Price :" + form.getShowtime().getTicketPrice());
		Paragraph p8 = new Paragraph("Show start time :" + form.getShowtime().getShowStartTime());
		Paragraph p9 = new Paragraph("Show date :" + form.getShowtime().getDate());
		Paragraph p10 = new Paragraph("Enjoy your movie!");
		document.add(p1);
		document.add(p2);
		document.add(p3);
		document.add(p4);
		document.add(p5);
		document.add(p6);
		document.add(p7);
		document.add(p8);
		document.add(p9);
		document.add(p10);
		document.close();
	}

}
