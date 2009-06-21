/*
 * Copyright (c) 2009. Gridshore
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.gridshore.enquiry.web;

import nl.gridshore.enquiry.def.ChoiceDef;
import nl.gridshore.enquiry.def.EnquiryDef;
import nl.gridshore.enquiry.def.OpenQuestionDef;
import nl.gridshore.enquiry.def.QuestionDef;
import nl.gridshore.enquiry.def.SingleChoiceQuestionDef;
import nl.gridshore.enquiry.input.EnquiryInstance;
import org.apache.wicket.markup.html.WebPage;

import java.util.ArrayList;
import java.util.List;

public class EnquiryHomePage extends WebPage {

    public EnquiryHomePage() {
        List<QuestionDef> questions = new ArrayList<QuestionDef>();
        questions.add(new OpenQuestionDef("Please answer this open question"));
        questions.add(new SingleChoiceQuestionDef("Choose one", new ChoiceDef("option 1"), new ChoiceDef("option 2")));
        EnquiryInstance instance = new EnquiryInstance(new EnquiryDef("Dit is de Enquiry", questions));
        add(new EnquiryPanel("enquiry", instance));
    }
}
