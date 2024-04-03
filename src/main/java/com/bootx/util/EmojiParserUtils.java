package com.bootx.util;

import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;

/**
 * @author black
 */
public class EmojiParserUtils {

    public static String parseToHtmlHexadecimal(String text){
        if(StringUtils.isNotBlank(text)){
            return EmojiParser.parseToHtmlDecimal(text);
        }
        return text;
    }

    public static String parseToUnicode(String text){
        if(StringUtils.isNotBlank(text)){
            return EmojiParser.parseToUnicode(text);
        }
        return text;
    }

}
