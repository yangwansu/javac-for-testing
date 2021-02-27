package net.masil.testing.compile;

import net.masil.testing.compile.options.BuildDir;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OptionList {

    private final List<Option<?>> list;

    OptionList(List<Option<?>> list) {
        this.list = Collections.unmodifiableList(list);
    }

    public String buildDir() {
        Optional<?> option = retrieveOptionValue(BuildDir.OPTION_NAME);
        return option.map(Object::toString).orElse(BuildDir.defaultOption().getValue());
    }

    public List<String> toList() {
        List<Option<?>> list = new ArrayList<>(this.list);

        if (list.stream().noneMatch(o -> BuildDir.OPTION_NAME.equals(o.getName()))) {
            list.add(BuildDir.defaultOption());
        }

        return list.stream()
                .map(Option::toIterable)
                .flatMap(i -> StreamSupport.stream(i.spliterator(), false))
                .collect(Collectors.toList());
    }

    public Optional<?> retrieveOptionValue(String optionName) {
        return list.stream().filter(o -> Objects.equals(o.getName(), optionName)).map(Option::getValue).findFirst();
    }

    public int size() {
        return list.size();
    }

}
