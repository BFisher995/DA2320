InfixToPostfixConverter Implementation:
    This class uses various helper methods all for the purpose of accurately converting the infix expression into
    postfix notation. I realized that the isOperator method is not actually required in my strictrue, but the
    homework guide said to include it so I did. As far as my implementation of the conversion method, I simply
    create 2 empty StringBuffer and Stack objects then add to them and remove from them as needed. The method follows
    a hierarchy to determine which elements get added to the string, added to the temporary stack or popped from the
    stack to the string.

PostfixEvaluator Implementation:
    This class uses similar methods and a similar hierarchy to determine the order in which mathematical operation are
    completed in the postfix string. The temporary int res variable stores the temporary total value of the equation until
    it is popped to the variable ans and returned at the end of the evaluate method.

How to runt it:
    I'm still trying to figure this out myself.