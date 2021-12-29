# Jsoup-WebScraping
Jsoup WebScraping


Jsoup.connect("https://play.google.com/store/apps/details?id=com.copypasteit.mobilehut.latest.mobile.market")
                        .timeout(30000).get().select("div.hAyfc:nth-child(4)>" +
                                "span:nth-child(2) > div:nth-child(1)" +
                                "> span:nth-child(1)").first().ownText();
                                
                                //html er end dik teke start korte hoy
