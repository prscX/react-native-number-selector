import React, { PureComponent } from "react";
import { StyleSheet, ViewPropTypes, Platform } from "react-native";
import PropTypes from "prop-types";

import { requireNativeComponent } from "react-native";

class RNNumberSelector extends PureComponent {
  _onChange = (event) => {
    this.props.onChange && this.props.onChange(event.nativeEvent.value);
  };

  render() {
    let {
      items,
      selectedItem,
      spacing,
      fontSize,
      highlightedFontSize,
      textColor,
      highlightedTextColor,
      dividerColor,
      viewAnimation,
      fontFamily,
      dividerThickness,
      maxValue,
      minValue,
      value,
      wheelItemCount,
    } = this.props;

    return (
      <NumberSelector
        style={this.props.style}
        selectedItem={selectedItem}
        spacing={spacing}
        fontSize={fontSize}
        dividerThickness={dividerThickness}
        highlightedFontSize={highlightedFontSize}
        textColor={textColor}
        highlightedTextColor={highlightedTextColor}
        dividerColor={dividerColor}
        viewAnimation={viewAnimation}
        onChange={this._onChange}
        fontFamily={fontFamily}
        maxValue={maxValue}
        minValue={minValue}
        value={value}
        wheelItemCount={wheelItemCount}
      />
    );
  }
}

RNNumberSelector.propTypes = {
  ...ViewPropTypes,

  items: PropTypes.array,
  selectedItem: PropTypes.number,
  spacing: PropTypes.number,
  fontSize: PropTypes.number,
  highlightedFontSize: PropTypes.number,
  textColor: PropTypes.string,
  highlightedTextColor: PropTypes.string,
  dividerColor: PropTypes.string,
  viewAnimation: PropTypes.number,
  onChange: PropTypes.func,
  fontFamily: PropTypes.string,
  dividerThickness: PropTypes.number,
  wheelItemCount: PropTypes.number,
  value: PropTypes.number,
  minValue: PropTypes.number,
  maxValue: PropTypes.number,
};

RNNumberSelector.defaultProps = {
  spacing: 50,
  fontSize: 20,
  highlightedFontSize: 25,
  textColor: "#000000",
  highlightedTextColor: "#000000",
  dividerColor: "#000000",
  viewAnimation: 1,
  dividerThickness: 1,
  fontFamily: "system font",
  wheelItemCount: 3,
};

const NumberSelector = requireNativeComponent(
  "RNNumberSelector",
  RNNumberSelector,
  {
    nativeOnly: { onChange: true },
  }
);

export { RNNumberSelector };
