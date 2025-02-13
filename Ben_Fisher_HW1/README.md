InfixToPostfixConverter Implementation:
    This class uses various helper methods all for the purpose of accurately converting the infix expression into
    postfix notation. As far as my implementation of the conversion method, I simply create 2 empty StringBuffer and
    Stack objects then add to them and remove from them as needed. The method follows a hierarchy to determine which
    elements get added to the string, added to the temporary stack or popped from the stack to the string.

PostfixEvaluator Implementation:
    This class uses similar methods and a similar hierarchy to determine the order in which mathematical operation are
    completed in the postfix string. The temporary int res variable stores the temporary total value of the equation
    until it is popped to the variable ans and returned at the end of the evaluate method.

How to run it:
    You can run the calculator class by using the infix.txt and output.txt files as command arguments. This assumes that
    only single digit numbers are in the equation, grouping symbols are accurate, and division rounds to the lowest whole
    number. This code throws an error when not using a valid character such as letters, exponents, non-parenthetical
    grouping symbols, and so on.