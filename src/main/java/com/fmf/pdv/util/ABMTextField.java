package com.fmf.pdv.util;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.*;

public class ABMTextField extends JTextField
{
    private DecimalFormat format;
    private String decimal;

    public ABMTextField(DecimalFormat format)
    {
        this.format = format;

        decimal = Character.toString( format.getDecimalFormatSymbols().getDecimalSeparator() );

        setColumns( format.toPattern().length() );
        setHorizontalAlignment(JFormattedTextField.TRAILING);

        setText( format.format(0.0) );

        AbstractDocument doc = (AbstractDocument)getDocument();
        doc.setDocumentFilter( new ABMFilter() );
    }

    @Override
    public void setText(String text)
    {
        Number number = format.parse(text, new ParsePosition(0));

        if (number != null)
            super.setText( text );
    }

    public class ABMFilter extends DocumentFilter
    {
        public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
            throws BadLocationException
        {
            replace(fb, offs, 0, str, a);
        }

        public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
            throws BadLocationException
        {
            if (".0123456789".contains(str))
            {
                Document doc = fb.getDocument();
                StringBuilder sb = new StringBuilder( doc.getText(0, doc.getLength()) );

                int decimalOffset = sb.indexOf( decimal );

                if (decimalOffset != -1)
                {
                    sb.deleteCharAt(decimalOffset);
                    sb.insert(decimalOffset + 1, decimal);
                }

                sb.append(str);

                try
                {
                    String text = format.format( format.parse( sb.toString() ) );
                    super.replace(fb, 0, doc.getLength(), text, a);
                }
                catch(ParseException e) {}
            }
            else
                Toolkit.getDefaultToolkit().beep();
        }

        public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
            throws BadLocationException
        {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder( doc.getText(0, doc.getLength()) );

            int decimalOffset = sb.indexOf( decimal );

            if (decimalOffset != -1)
            {
                sb.deleteCharAt(decimalOffset);
                sb.insert(decimalOffset - 1, decimal);
            }

            sb.deleteCharAt( sb.length() - 1) ;

            try
            {
                String text = format.format( format.parse( sb.toString() ) );
                super.replace(fb, 0, doc.getLength(), text, null);
            }
            catch(ParseException e) {}
        }
    }

    private static void createAndShowUI()
    {
        DecimalFormat format = new DecimalFormat("###,##0.00");
        ABMTextField abm = new ABMTextField( format );

        JPanel panel = new JPanel();
        panel.add( abm );

        JFrame frame = new JFrame("ABMTextField");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add( panel );
        frame.setSize(200, 200);
        frame.setLocationByPlatform( true );
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowUI();
            }
        });
    }
}