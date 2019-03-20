import React, { PureComponent } from "react";
import { StyleSheet, ViewPropTypes, Platform } from "react-native";
import PropTypes from "prop-types";

import { requireNativeComponent } from "react-native";

class RNNumberSelector extends PureComponent {
  _onChange = event => {
    this.props.onChange && this.props.onChange(event.nativeEvent.value);
  };

  render() {
    let {
      items,
      selectedItem,
      spacing,
      highlightedFontSize,
      fontSize,
      textColor,
      highlightedTextColor,
      viewAnimation
    } = this.props;

    return (
      <NumberSelector
        style={this.props.style}
        items={items}
        selectedItem={selectedItem}
        spacing={spacing}
        highlightedFontSize={highlightedFontSize}
        fontSize={fontSize}
        textColor={textColor}
        highlightedTextColor={highlightedTextColor}
        viewAnimation={viewAnimation}
        onChange={this._onChange}
      />
    );
  }
}

RNNumberSelector.propTypes = {
  ...ViewPropTypes,

  spacing: PropTypes.number,
  highlightedFontSize: PropTypes.number,
  fontSize: PropTypes.number,
  textColor: PropTypes.string,
  highlightedTextColor: PropTypes.string,
  viewAnimation: PropTypes.number,
  onChange: PropTypes.func
};

RNNumberSelector.defaultProps = {
  spacing: 50,
  highlightedFontSize: 25,
  fontSize: 20,
  textColor: "#000000",
  highlightedTextColor: "#000000",
  viewAnimation: 1
};

const NumberSelector = requireNativeComponent(
  "RNNumberSelector",
  RNNumberSelector,
  {
    nativeOnly: { onChange: true }
  }
);

export { RNNumberSelector };
