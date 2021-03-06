// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Random;

/** Servlet that responds with the current date. */
@WebServlet("/phrase")
public class PhraseServlet extends HttpServlet {

    private int prevIndex = -1;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Random rand = new Random(); // instance of random class

        String[] phrases = { "\"The Best Way To Get Started Is To Quit Talking And Begin Doing.\" - Walt Disney",
                "\"The Pessimist Sees Difficulty In Every Opportunity. The Optimist Sees Opportunity In Every Difficulty.\" - Winston Churchill",
                "\"Don't Let Yesterday Take Up Too Much Of Today.\" - Will Rogers",
                "\"You Learn More From Failure Than From Success. Don't Let It Stop You. Failure Builds Character.\" - Unknown",
                "\"It's Not Whether You Get Knocked Down, It's Whether You Get Up.\" - Vince Lombardi",
                "\"If You Are Working On Something That You Really Care About, You Don't Have To Be Pushed. The Vision Pulls You.\" - Steve Jobs",
                "\"People Who Are Crazy Enough To Think They Can Change The World, Are The Ones Who Do.\" - Rob Siltanen",
                "\"We May Encounter Many Defeats But We Must Not Be Defeated.\" - Maya Angelou",
                "\"Knowing Is Not Enough; We Must Apply. Wishing Is Not Enough; We Must Do.\" - Johann Wolfgang Von Goethe",
                "\"We Generate Fears While We Sit. We Overcome Them By Action.\" - Dr. Henry Link",
                "\"Whether You Think You Can Or Think You Can't, You're Right.\" - Henry Ford",
                "\"The Man Who Has Confidence In Himself Gains The Confidence Of Others.\" - Hasidic Proverb" };

        int randomPhraseIndex = rand.nextInt(phrases.length);

        while (prevIndex == randomPhraseIndex) {
            randomPhraseIndex = rand.nextInt(phrases.length);
        }

        prevIndex = randomPhraseIndex;

        response.setContentType("text/html;");
        response.getWriter().println(phrases[randomPhraseIndex]);
    }
}
