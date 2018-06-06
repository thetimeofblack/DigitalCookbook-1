package de.fhluebeck.group3.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import de.fhluebeck.group3.controller.MainFrameController;
import de.fhluebeck.group3.model.Ingredient;
import de.fhluebeck.group3.model.Recipe;
import de.fhluebeck.group3.model.Step;
import de.fhluebeck.group3.view.Template;
import javafx.stage.FileChooser;

/**
 * 
 * @author Yichen.Hua on 2018/06/05.
 */
public final class ExportPDF {

	private static Font bodyFont;

	private static Font titleFont;

	private static Font subTitleFont;

	public ExportPDF() throws DocumentException, IOException {

		bodyFont = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);

		// BaseFont base = BaseFont.createFont("resources/Ormont_Light.ttf",
		// BaseFont.WINANSI, BaseFont.EMBEDDED);

		titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);

		subTitleFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

	}

	public boolean createFile(Recipe recipe) {

		boolean flag = false;

		try {

			Document document = new Document();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Cookbook pdf");

			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.pdf)", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);

			File ioFile = fileChooser.showSaveDialog(Template.getPrimaryStage());

			File file = new File(ioFile.getAbsolutePath());

			PdfWriter.getInstance(document, new FileOutputStream(file));

			document.open();

			if (recipe != null) {
				addMetaData(document);

				addTitlePage(document, recipe);

				addContent(document, recipe);
			}

			document.close();

			flag = true;

		} catch (Exception e) {

			e.printStackTrace();

			return false;

		}

		return flag;
	}

	/**
	 * Add Meta-data to the PDF which can be viewed in Adobe, Note that I refer to
	 * the code from seniors.
	 * 
	 * @param document
	 */
	private static void addMetaData(Document document) {

		document.addTitle("My Cookbook");

	}

	/**
	 * Add title page with title "Digital Cook-book Recipe", Note that I refer to
	 * the code from seniors.
	 * 
	 * @param document
	 * @throws DocumentException
	 */
	private static void addTitlePage(Document document, Recipe recipe) throws DocumentException {

		StringBuilder builder = new StringBuilder();

		builder.append("Digital Cookbook Recipe \n").append(recipe.getRecipeName() + "\n")
				.append("By Group3 on SWEII, L��beck University of Applied Sciences, Germany");

		Paragraph title = new Paragraph(builder.toString(), titleFont);

		PdfPCell cell = new PdfPCell();

		// add title to a cell
		cell.addElement(title);

		title.setAlignment(Element.ALIGN_CENTER);

		cell.setNoWrap(false);

		cell.setPaddingTop(300);

		// make the border invisible
		cell.setBorder(Rectangle.NO_BORDER);

		cell.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPTable table = new PdfPTable(1);

		// add cell to a table
		table.addCell(cell);

		// add the table to the page
		document.add(table);

		// start new page
		document.newPage();
	}

	/**
	 * Add recipes to each page, Note that I refer to the code from seniors.
	 * 
	 * @param document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void addContent(Document document, Recipe recipe) throws DocumentException, IOException {

		// page number starts from 1
		int pageNum = 2;

		// add page number
		Paragraph num = new Paragraph(String.valueOf(pageNum));

		num.setAlignment(Element.ALIGN_CENTER);

		PdfPCell pageNumCell = new PdfPCell();

		// add page number to cell
		pageNumCell.addElement(num);

		pageNumCell.setVerticalAlignment(Element.ALIGN_BOTTOM);

		pageNumCell.setBorder(Rectangle.NO_BORDER);

		PdfPTable pageNumTable = new PdfPTable(1);

		// add cell to table
		pageNumTable.addCell(pageNumCell);

		pageNumTable.setExtendLastRow(true);

		// set the title of this page(recipe name)
		Paragraph placeHolder = new Paragraph(Chunk.NEWLINE);

		Paragraph rName = new Paragraph(recipe.getRecipeName(), bodyFont);

		Chapter details = new Chapter(placeHolder, 0);

		details.add(Chunk.NEWLINE);

		details.add(rName);

		details.setNumberDepth(0);

		// add image
		Image image = null;

		try {

			image = Image.getInstance(MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + recipe.getImagePath());

		} catch (FileNotFoundException ex) {

			image = Image.getInstance(MainFrameController.RECIPE_IMAGE_DEFAULT_PATH + "fileNotFound.jpg");
		}

		image.setAbsolutePosition(400, 680);

		image.scaleToFit(150, 150);

		details.add(image);

		// preparation time and cooking time
		details.add(Chunk.NEWLINE);

		details.add(new Paragraph(String.valueOf("Preparation time: " + recipe.getPreparationTime() + "min")));

		details.add(new Paragraph(String.valueOf("Cooking time: " + recipe.getCookingTime() + "min")));

		details.add(new Paragraph(String.valueOf("Cooking time: " + recipe.getAvailablePeople())));

		details.add(new Chunk(new LineSeparator()));

		// description
		details.add(Chunk.NEWLINE);

		details.add(new Paragraph(recipe.getDescription()));

		details.add(Chunk.NEWLINE);

		// ingredients
		details.add(Chunk.NEWLINE);

		details.add(new Paragraph("Ingredient", subTitleFont));

		details.add(new Chunk(new DottedLineSeparator()));

		int ingredientOrder = 1;

		if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
			for (Ingredient ingredient : recipe.getIngredients()) {

				details.add(new Paragraph(String.valueOf(ingredientOrder) + ". " + ingredient.getIngredientName() + " "

						+ ingredient.getQuantity() + " "

						+ ingredient.getUnit() + "\n"));

				ingredientOrder += 1;
			}
		}
		// steps
		details.add(Chunk.NEWLINE);

		details.add(new Paragraph("Step", subTitleFont));

		details.add(new Chunk(new DottedLineSeparator()));

		if (recipe.getSteps() != null && recipe.getSteps().size() > 0) {
			for (Step step : recipe.getSteps()) {

				details.add(new Paragraph(String.valueOf(step.getStepOrder()) + ". " + step.getContent()));

			}
		}
		// add recipe information
		document.add(details);

		// add page number
		document.add(pageNumTable);

		// start a new page
		document.newPage();

	}

}
