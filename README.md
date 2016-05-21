# Tradeit

This is an under-construction Java web application. The goal is to build a cragslist-like website.

Function Milestones:
1. User Registration/Login                                                                                      -added
    (1) Password Hashing                                                                                        -added  
2. Session Management                                                                                           -added  
    (1) implement login/logout session cookie management                                                        -added
    (2) implement authentication filter to check user in-session status (excluding login/registration page).    -added
3. Post Create                                                                                                  -added
    (1) Add Image upload                                                                                        -added
    (2) Add multiple image support
    (3) ensure image file secure
4. Post View                                                                                                    -added
    (1) add "view post" function in home page
    (2) get post information from server via json/ajax

5. Security (if not mentioned above)
    (1) page cache control                                                                                      -added
    (2) response cookie attribute
    (3) page x-frame-options
