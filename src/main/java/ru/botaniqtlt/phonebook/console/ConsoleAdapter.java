package ru.botaniqtlt.phonebook.console;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.botaniqtlt.phonebook.console.form.Form;
import ru.botaniqtlt.phonebook.console.form.FormResponse;
import ru.botaniqtlt.phonebook.console.form.MainForm;

import javax.annotation.PostConstruct;
import java.util.Stack;

/**
 * Адаптер для работы с консольным UI
 */
@Component
public class ConsoleAdapter  {


    private final Stack<Form> formStack = new Stack<>();

    private final ApplicationContext context;


    public ConsoleAdapter(ApplicationContext context) throws Exception {
        this.context=context;
        createForm(MainForm.class);
    }

    private void createForm(Class<? extends Form> formClass) throws Exception {
        try {
            Form form = context.getBean(formClass);
            formStack.push(form);
        }catch (Throwable e){
            throw e;
        }
    }

    @PostConstruct
    public void run() throws Exception {
        Form form;
        boolean run = true;
        while (run) {
            form = formStack.peek();
            if (form == null) {
                return;
            }
            FormResponse<? extends Form> action = form.run();
            run = processResponse(run, action);
        }
    }

    private boolean processResponse(boolean run, FormResponse<? extends Form> action) throws Exception {
        switch (action.getAction()) {
            case GO:
                createForm(action.getLink());
                break;
            case BACK:
                formStack.pop();
                break;
            case STAY:
                //повторяем текущую форму
                break;
            case EXIT:
                //В случае EXIT переходим к дефолтному случаю
            default:
                run = false;
                break;
        }
        return run;
    }

}

